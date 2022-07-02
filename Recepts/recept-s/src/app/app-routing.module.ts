import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MedlcComponent } from './medlc/medlc.component';
import { RecformComponent } from './recform/recform.component';
import { HistoryComponent } from './history/history.component';

const routes: Routes = [
  {path:'home',component: MedlcComponent},
  {path:'receptform',component: RecformComponent},
  {path:'history',component: HistoryComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
