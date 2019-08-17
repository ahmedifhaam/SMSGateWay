import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {ProcessingComponent} from "./components/processing/processing.component";
import {AdminpanelComponent} from "./components/adminpanel/adminpanel.component";
import {PaswordresetComponent} from "./components/paswordreset/paswordreset.component";

const routes: Routes = [
  {path:'login',component: LoginComponent},
  {path:'processing',component:ProcessingComponent},
  {path:'',redirectTo:'/login',pathMatch:'full'},
  {path:'adminpanel',component:AdminpanelComponent},
  {path:'password_reset',component:PaswordresetComponent}
  // {path:'/',redirectTo:'login',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,
    { enableTracing: false } //enable this for debugging
    )],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
