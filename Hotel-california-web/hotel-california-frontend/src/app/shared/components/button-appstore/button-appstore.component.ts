import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-button-appstore',
  templateUrl: './button-appstore.component.html',
  styleUrls: ['./button-appstore.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ButtonAppstoreComponent implements OnInit {

  @Input() app!:string;
  @Input() text!:string;
  logos = {
    appStore:"",
    googlePlay:""
  }
  constructor() { }

  ngOnInit(): void {
  }

}
