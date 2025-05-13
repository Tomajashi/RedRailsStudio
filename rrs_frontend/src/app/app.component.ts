import { Component } from '@angular/core';
import { MapComponent } from './components/map/map.component';
import { SettingsComponent } from './components/settings/settings.component';
import { ResoursesComponent } from './components/ressourses/resourses.component';
import { APISService } from './services/apis.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [MapComponent, ResoursesComponent],  // Keep only MapComponent
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'rrs_frontend';
}
