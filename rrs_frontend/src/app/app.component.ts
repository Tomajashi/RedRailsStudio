import { Component } from '@angular/core';
import { SettingsComponent } from './components/settings/settings.component';
import { CommonModule } from '@angular/common';
import { ResoursesComponent } from './components/resourses/resourses.component';
import { MyCounterComponent } from './my-counter/my-counter.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ MyCounterComponent, CommonModule],  // Keep only MapComponent
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'rrs_frontend';
}
