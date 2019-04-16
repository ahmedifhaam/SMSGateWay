import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable,of} from "rxjs";
import {Authority, LoginResponse} from "../login.service";

@Injectable({
  providedIn: 'root'
})
export class DatastoreService {
  isLoggedIn:boolean =false;
  loginresponse:LoginResponse;
  update:BehaviorSubject<boolean>;
  constructor() {
    this.update = new BehaviorSubject<boolean>(false)
  }

  public setisLoggedIn(value:boolean){
    this.isLoggedIn = value;
    this.update.next(value);
  }

  public getsLoggedIn(){
    return this.isLoggedIn;
  }

  public getLoginResponse(){
    return this.loginresponse;
  }

  public setLoginResponse(value){
    this.loginresponse = value;
  }

  public subscribeUpdate(){
    return this.update;
  }

  public hasAuthority(req:string){
    if(!this.loginresponse){

      return false;

    }else{
      var output = false;
      this.loginresponse.user.authorities.forEach((authority,index,array)=>{

        if(authority.authority==req){

          output = true;

        }
      })
      return output


    }
  }
}

export interface Action {
  type:string;
  payload?:any
  
}

interface Reducer<T> {
  (state:T,action:Action):T;
}
