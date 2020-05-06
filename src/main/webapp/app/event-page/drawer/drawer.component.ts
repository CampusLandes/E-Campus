import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'jhi-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.scss']
})
export class DrawerComponent implements OnInit {
  @Input() value: any;
  @Input() type: String = '';
  @Input() user: String = '';

  constructor() {}

  ngOnInit(): void {}

  getRow(): number {
    if (this.type === 'edit') {
      return 1;
    } else {
      return 0;
    }
  }
}
