import { Routes } from '@angular/router';
import * as homepageComponent from './pages/homepage/homepage.component';

export const routes: Routes = [
  { path: '', component: homepageComponent.HomepageComponent }, // Default route
  { path: '**', redirectTo: '' } // Fallback route
];
