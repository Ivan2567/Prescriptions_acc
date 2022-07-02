import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegFormComponent } from './reg-form/reg-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MyNavComponent } from './my-nav/my-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';


import { ClNavComponent } from './cl-nav/cl-nav.component';
import { MedlcComponent } from './medlc/medlc.component';
import { RecformComponent } from './recform/recform.component';
import { CardComponent } from './card/card.component';
import { HistoryComponent } from './history/history.component';
import { LecarstvoComponent } from './lecarstvo/lecarstvo.component';

import { SomeDataService } from './services/some-data.service';
import { TestReceptService } from './services/test-recept.service';


@NgModule({
  declarations: [
    AppComponent,
    RegFormComponent,
    MyNavComponent,
    ClNavComponent,
    MedlcComponent,
    RecformComponent,
    CardComponent,
    HistoryComponent,
    
    LecarstvoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule,
    MatExpansionModule
  ],
  providers: [SomeDataService,TestReceptService,],
  bootstrap: [AppComponent]

})
export class AppModule { }
