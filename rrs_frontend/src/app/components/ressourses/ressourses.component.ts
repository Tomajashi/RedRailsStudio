import { Component, OnInit } from '@angular/core';
import { APISService } from '../../services/apis.service';

@Component({
  selector: 'app-ressourses',
  imports: [],
  templateUrl: './ressourses.component.html',
  styleUrl: './ressourses.component.scss'
})
export class RessoursesComponent implements OnInit {
  displayedValue: string =''; // Display value ist die ausgegebene wert auf den bild schirm
  
  constructor(private apiService: APISService) {
    
  }
  
  ngOnInit(private apiService: APISService) {
    this.apiService.getData('ressources').subscribe((response) => {
      this.displayedValue = response.value; //setzt der zahl am bildshirm um
    })
  }

}
