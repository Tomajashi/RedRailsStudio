import { Component } from '@angular/core';
import { MapComponent } from './components/map/map.component';
import { SettingsComponent } from './components/settings/settings.component';
import { ResoursesComponent } from './components/resourses/resourses.component';
import { APISService } from './services/apis.service';
import { CounterComponent } from './counter/counter.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [MapComponent, ResoursesComponent],  // Keep only MapComponent
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'rrs_frontend';
  sessionId = new Date().getTime().toString();

  constructor(private apiService: APISService) {


    this.apiService.postNewPlayer('testsession', 'Player1').subscribe((response)=> {console.log('New Player')},
    (error)=> {console.log('Error player')}); //Startet session; //erstellt eine Player

    this.apiService.startSession('testsession').subscribe((response)=> {console.log('Session started')},
    (error)=> {console.log('Error starting')}); //Startet session

  }

  onCreateSession() {
    this.apiService.createSession(this.sessionId).subscribe((response) => {console.log('Session created')},
    (error) => {console.log('Error creating session')},
     );  //erstellt ein Session
  }

  onKillSession(){
    this.apiService.killSession(this.sessionId).subscribe((response) => {console.log('Session killed')},
    (error) => {console.log('Error killing session')},
  );
  }

//TODO:Die Name des Sessions Soll Mehr nachvollziehbar sein. bsp Session1, Session2 usw.
//TODO:Es Sollte textfeld geben fur eingabe von Playername und joinSession button

}
