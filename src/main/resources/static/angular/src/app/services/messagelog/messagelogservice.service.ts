import { Injectable } from '@angular/core';
import {Messagelog} from "../../models/messagelog";
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class MessagelogserviceService {
  url:string = "/api/messagelogs";
  filterurl:string = "/api/filtermessagelogs?"
  filefiltereddownload = "/api/filemessagelogs?"
  constructor(private http:HttpClient) { }

  getallmessagelog(){
    return this.http.get(this.url);
  }

  filteredmessagelogs(query:string){
    return this.http.get(this.filterurl+query);
  }

  filemessagelogs(query:string){
    window.open("/sms"+this.filefiltereddownload+query);
  }
}
