import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {
  MatTabsModule,
  MatInputModule,
  MatCardModule,
  MatProgressSpinnerModule,
  MatAutocompleteModule,
  MatToolbarModule,
  MatTableModule,
  MatPaginatorModule,
  MatExpansionModule, MatCheckboxModule, MatListModule, MatChipsModule, MatSelectModule
} from "@angular/material";
import { BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {Browser} from "selenium-webdriver";
import { MatDividerModule} from "@angular/material";
import { ReactiveFormsModule,FormsModule} from "@angular/forms";
import { LoginComponent } from './components/login/login.component';
import { ProcessingComponent } from './components/processing/processing.component';
import {FileSelectDirective} from "ng2-file-upload";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatGridListModule} from "@angular/material";
import { HeaderComponent } from './components/header/header.component';
import { MatButtonModule} from "@angular/material";
import { AdminpanelComponent } from './components/adminpanel/adminpanel.component';
import { SuperadminpanelComponent } from './components/superadminpanel/superadminpanel.component';
import {Requestinterceptor} from "./interceptor/requestinterceptor";
import { PaswordresetComponent } from './components/paswordreset/paswordreset.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProcessingComponent,
    FileSelectDirective,
    HeaderComponent,
    AdminpanelComponent,
    SuperadminpanelComponent,
    PaswordresetComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatTabsModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatDividerModule,
    ReactiveFormsModule,FormsModule,
    MatCardModule,MatProgressSpinnerModule,MatAutocompleteModule,
    HttpClientModule,
    MatGridListModule,
    MatToolbarModule,MatButtonModule,
    MatTableModule,MatPaginatorModule,
    MatExpansionModule,
    MatCheckboxModule,MatListModule,MatChipsModule,MatSelectModule


  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:Requestinterceptor,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
