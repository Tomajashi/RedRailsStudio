import { Component, OnInit } from '@angular/core';
import { APISService } from '../../services/apis.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-resourses',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './resourses.component.html',
  styleUrls: ['./resourses.component.scss']
})
export class ResoursesComponent implements OnInit {
  displayedValue: number = 0; // Display value ist die ausgegebene wert auf den bild schirm
  coinZahl: number = 0; // Anzahl der Coins

  constructor(private apiService: APISService) {
    
  }
  ngOnInit(): void {
    this.apiService.getTotalTrains().subscribe((totalTrains) => {
      this.displayedValue = totalTrains; 
    });
    this.apiService.getDBCoins().subscribe((coins) => { //holt DB coins vom backend service
      this.coinZahl = coins; 
    });
  }

}
