import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/user";
import {stringify} from "querystring";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url:string = "/api/user";
  urlalluser:string = "/api/allusers"
  resetpasswordurl:string="api/cuser"
  constructor(private http:HttpClient) { }

  getallusers(){
    return this.http.get(this.urlalluser);
  }

  updateuser(user:User){
    return this.http.put(this.url,user);
  }

  addnewuser(user:User){
    return this.http.post(this.url,user);
  }

  deleteuser(user:User){
    return this.http.delete(this.url+"/"+user.username);
  }

  resetuserpassword(user:User){
    return this.http.put(this.resetpasswordurl,user);
  }
}
