import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  // IMPORTANTE: Mantendo os imports necessários para o menu funcionar
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css' // Verifique se o arquivo se chama app.component.css ou app.css
})
export class AppComponent { // <--- VOLTOU PARA 'AppComponent'
  title = 'fabsoft-frontend';
}