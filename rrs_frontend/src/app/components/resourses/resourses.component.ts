import { Component, OnInit } from '@angular/core';
import { APISService } from '../../services/apis.service';

@Component({
  selector: 'app-resourses',
  imports: [],
  templateUrl: './resourses.component.html',
  styleUrl: './resourses.component.scss'
})
export class ResoursesComponent implements OnInit {
  displayedValue: string =''; // Display value ist die ausgegebene wert auf den bild schirm
  
  constructor(private apiService: APISService) {
    
  }

  ngOnInit(): void {
    this.apiService.getData().subscribe((response) => {
      this.displayedValue = response;
    });
  }

}
