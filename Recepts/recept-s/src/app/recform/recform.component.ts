import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { TestReceptService } from '../services/test-recept.service'
import { SomeDataService } from '../services/some-data.service';

@Component({
  selector: 'app-recform',
  templateUrl: './recform.component.html',
  styleUrls: ['./recform.component.css']
})
export class RecformComponent  {
constructor(private testS: TestReceptService,public someS:SomeDataService) {}
  // diagnoz: string = this.someS.actualdiagnoz;
  // status: string = this.someS.actualstatus;
  // name: string = "";
  // secname: string = "";
  desc: string = "";
  


  createRec(){
    // this.diagnoz = this.someS.actualdiagnoz;
    // this.status = this.someS.actualstatus;
    // this.testS.polis = this.diagnoz
    // this.testS.surname = this.sname
    // this.testS.name = this.name
    // this.testS.secname = this.secname
    // this.testS.discr = this.desc

    // this.diagnoz ="";
    // this.sname ="";
    // this.name ="";
    // this.secname ="";
    // this.desc ="";
  }

}
