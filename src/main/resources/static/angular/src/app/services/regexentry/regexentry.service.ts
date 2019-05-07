import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegexEntry} from "../../models/RegexEntry";

@Injectable({
  providedIn: 'root'
})
export class RegexentryService {
  url:string = "/api/regexentry"


  constructor(private httpClient:HttpClient ) { }


  getAllEntries(){
    return this.httpClient.get(this.url+"all");
  }

  updateEntry(regexEntry:RegexEntry){
    return this.httpClient.put(this.url,regexEntry);
  }

  insertEntry(regexEntry:RegexEntry){
    return this.httpClient.post(this.url,regexEntry);
  }

  deleteEntry(regexEntry:RegexEntry){
    return this.httpClient.delete(this.url+"/"+regexEntry.name);
  }
}
