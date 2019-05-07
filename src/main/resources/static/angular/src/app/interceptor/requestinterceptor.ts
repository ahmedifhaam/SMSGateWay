import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";

export class Requestinterceptor implements HttpInterceptor{

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(tap((event:HttpEvent<any>)=>{
      console.log(event);
    },error=>{
      console.log("error");
      alert("Unexpected Response error,Retry after sometime if error persists, please contact admin, ")
      console.log(error);
    }));

  }


}
