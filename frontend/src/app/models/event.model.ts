export interface Event {
    id?: number;
    title: string;
    description: string;
    date: Date;
    location: string;
    maxParticipants: number;
    currentParticipants?: number;
    eventType: string;
    imageUrl?: string;
    organizerName?: string;
    isActive?: boolean;
    createdAt?: Date;
}

export interface EventRequest {
    title: string;
    description: string;
    date: Date;
    location: string;
    maxParticipants: number;
    eventType: string;
    imageUrl?: string;
} 