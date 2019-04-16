import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SendsmsserviceService {
  url = "/api/sendSms";
  constructor( private http:HttpClient) { }

  sendsms(phonenumber:string,message:string,mask:string){
    var data = {'phonenumber':phonenumber,'message':message,'status':'REQ','mask':mask};
    return this.http.post(this.url,data);
  }

}
