import { Router, Routes } from '@angular/router';
import * as homepageComponent from './pages/homepage/homepage.component';

import { AppComponent } from './app.component';
import {RessoursesComponent} from './components/ressourses/ressourses.component';

export const routes: Routes = [
  { path: '', component: homepageComponent.HomepageComponent }, // Default route
  { path: '**', redirectTo: '' } // Fallback route
];
