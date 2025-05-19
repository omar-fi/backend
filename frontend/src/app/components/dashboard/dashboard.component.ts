import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { AuthService } from '../../services/auth.service';
import { Event } from '../../models/event.model';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  events: Event[] = [];
  currentUser: User | null = null;
  loading: boolean = true;
  error: string = '';

  constructor(
    private eventService: EventService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    if (!this.currentUser) {
      this.router.navigate(['/login']);
      return;
    }
    this.loadEvents();
  }

  loadEvents(): void {
    this.loading = true;
    this.eventService.getAllEvents().subscribe({
      next: (response) => {
        this.events = response.content;
        this.loading = false;
      },
      error: (error) => {
        this.error = 'Erreur lors du chargement des événements';
        this.loading = false;
        console.error('Error loading events:', error);
      }
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isOrganizer(): boolean {
    return this.currentUser?.role === 'ORGANIZER';
  }

  isAdmin(): boolean {
    return this.currentUser?.role === 'ADMIN';
  }
} 