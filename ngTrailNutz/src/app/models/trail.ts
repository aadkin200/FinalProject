import { Amenity } from "./amenity";
import { Difficulty } from "./difficulty";
import { Routetype } from "./routetype";
import { TrailImage } from "./trail-image";
import { TrailResource } from "./trail-resource";
import { User } from "./user";
import { Comment } from "./comment";

export class Trail {
  id: number;
  name: string;
  elevationChangeFeet: number;
  trailheadLatitude: string;
  trailheadLongitude: string;
  city: string;
  state: string;
  hazards: string;
  wildlife: string;
  details: string;
  distanceMiles: number;
  createdAt: string;
  updatedAt: string;
  enabled: boolean;
  approved: boolean;
  users: User[];
  user: User;
  difficulty: Difficulty;
  routeType: Routetype;
  trailImages: TrailImage[];
  trailResource: TrailResource[];
  comments: Comment[];
  amenities: Amenity[];


  constructor(
    id: number = 0,
    name: string = '',
    elevationChangeFeet: number = 0,
    trailheadLatitude: string = '',
    trailheadLongitude: string = '',
    city: string = '',
    state: string = '',
    hazards: string = '',
    wildlife: string = '',
    details: string = '',
    distanceMiles: number = 0,
    createdAt: string = '',
    updatedAt: string = '',
    enabled: boolean = true,
    approved: boolean = true,
    users: User[] = [],
    user: User = new User(),
    difficulty: Difficulty = new Difficulty(),
    routeType: Routetype = new Routetype(),
    trailImages: TrailImage[] = [],
    trailResource: TrailResource[] = [],
    comments: Comment[] = [],
    amenities: Amenity[] = []
  ) {
    this.id = id;
    this.name = name;
    this.elevationChangeFeet = elevationChangeFeet;
    this.trailheadLatitude = trailheadLatitude;
    this.trailheadLongitude = trailheadLongitude;
    this.city = city;
    this.state = state;
    this.hazards = hazards;
    this.wildlife = wildlife;
    this.details = details;
    this.distanceMiles = distanceMiles;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.enabled = enabled;
    this.approved = approved;
    this.users = users;
    this.user = user;
    this.difficulty = difficulty;
    this.routeType = routeType;
    this.trailImages = trailImages;
    this.trailResource = trailResource;
    this.comments = comments;
    this.amenities = amenities;
  }

}
