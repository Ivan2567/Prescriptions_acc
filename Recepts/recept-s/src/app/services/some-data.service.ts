import { Injectable } from "@angular/core";

@Injectable({providedIn: 'root' })
export class SomeDataService{
    data: boolean = false
    actualrecid: number = 0
    actualdiagnoz: string = ""
    actualstatus: string = ""
    actualdatef: string = ""
    actualsrok: number = 0
}