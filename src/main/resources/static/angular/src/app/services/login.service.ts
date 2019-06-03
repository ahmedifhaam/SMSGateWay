import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {HeaderComponent} from "../components/header/header.component";
import {DatastoreService} from "./datastore/datastore.service";
import {stringify} from "querystring";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  loginUrl = "/perform_login";
  logouturi = "/perform_logout";
  islogedinUrl = "/api/user"
  constructor(private http:HttpClient,private router :Router,private datastore:DatastoreService) {

  }

  login(data){
    return this.http.post(this.loginUrl,data).subscribe(
      (response:LoginResponse) =>{
        console.log("response is : ");
        console.log(response);
        if(response.response=="SUCCESSFUL"){
          this.datastore.setLoginResponse(response);
          this.datastore.setisLoggedIn(true);
          this.router.navigateByUrl("/processing");

        }else if (response.response=="FAILED"){
          alert("Failed to Login, check username and password ")
        }
      }
    ,error1 => {
        console.log("ter");
        alert(stringify(error1))
      })
  }

  islogedin(){
    return this.http.get(this.islogedinUrl).subscribe(
      (response:LoginResponse)=>{
        console.log("response is : ");
        console.log(response);
        if(response.response=="SUCCESSFUL"){
          this.datastore.setLoginResponse(response);
          this.datastore.setisLoggedIn(true);
          this.router.navigateByUrl("/processing");

        }
      }
    )
  }



  logout(){
    return this.http.get(this.logouturi).subscribe(
      response=>{
        console.log(response);
        if(response['response']=="SUCCESSFUL"){
          this.datastore.setisLoggedIn(false);
          this.datastore.setLoginResponse(null);
          this.router.navigateByUrl("");
        }
      },
      error1 => {
        alert(stringify(error1));
      },
    )
  }

// {"user":{"password":null,"username":"user","authorities":[{"authority":"ADMIN"},{"authority":"USER"}],
// "accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true},"response":"SUCCESSFUL"}
}

export interface Authority {
  authority:string;
}

export interface LoginResponse {
  user:{password:string,username:string,authorities:Authority[],accountNonExpired:boolean
  accountNonLocked:boolean,credentialsNonExpired:boolean,enabled:boolean}
  response:string;
}
