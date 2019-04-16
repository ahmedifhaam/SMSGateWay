import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MaskserviceService {
  url:string = "/api/mask";
  constructor(private http:HttpClient) { }

  getallmasks(){
    return this.http.get(this.url);
  }

  addmask(value:string){
    var data = {"value":value};
    return this.http.put(this.url,data);
  }
}
