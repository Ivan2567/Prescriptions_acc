import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { SomeDataService } from '../services/some-data.service';
import { TestReceptService } from '../services/test-recept.service';
import { SmallRec } from '../classes/SmallRec';
import axios from 'axios';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  cards: SmallRec[] = [];
  color:string = ""  
  constructor(private serV: SomeDataService) {}
  open(actualrecid:number, actualdiagnoz:string, actualstatus:string, actualdatef:string, actualsrok:number){ 
    this.serV.data = !this.serV.data;
    this.serV.actualrecid = actualrecid;
    this.serV.actualdiagnoz = actualdiagnoz;
    this.serV.actualstatus = actualstatus;
    this.serV.actualdatef = actualdatef;
    this.serV.actualsrok = actualsrok;
  }

  comlete(){
    // this.color = "grey";
    (<HTMLInputElement> document.getElementById("btn")).disabled = true
    axios.post('http://localhost:8080/Users/changeSt', {idrec:1}
    )
    .then(
      (res)=>{console.log( res.data.msg);}
    )
  }
  ngOnInit(): void {
    axios.post('http://localhost:8080/Users/getPac', {polis:"0000000000000000", filter: [] }
    // ,{ headers: {"Access-Control-Allow-Origin" : "http://localhost:8080/Users/getPac" }}
    )
    .then(
      (res)=>{console.log( res.data.msg);
        this.cards = res.data.recepts;
        console.log( this.cards);}
    )
  }
}
