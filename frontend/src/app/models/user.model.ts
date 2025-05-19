export interface User {
    id?: number;
    firstName: string;
    lastName: string;
    email: string;
    role: 'ADMIN' | 'ORGANIZER' | 'PARTICIPANT';
}

export interface AuthResponse {
    token: string;
    email: string;
    firstName: string;
    lastName: string;
    role: string;
}

export interface LoginRequest {
    email: string;
    password: string;
}

export interface RegisterRequest {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    role?: 'ADMIN' | 'ORGANIZER' | 'PARTICIPANT';
} 