import { Component, OnInit } from '@angular/core';
import { APISService } from '../../services/apis.service';

@Component({
  selector: 'app-ressourses',
  imports: [],
  templateUrl: './ressourses.component.html',
  styleUrl: './ressourses.component.scss'
})
export class RessoursesComponent implements OnInit {
  data: any[];

  constructor(private apiService: APISService) {
    this.data = []; // Initialize data as an empty array
  }
  ngOnInit(){
    
  }

}
