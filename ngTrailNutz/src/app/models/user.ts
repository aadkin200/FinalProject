import { Trail } from "./trail";
import { TrailImage } from "./trail-image";

export class User {
  id:number;
  username:string;
  role:string;
  password:string;
  enabled: boolean;
  favoriteTrailFood:string;
  firstName:string;
  lastName:string;
  imgUrl:string;
  createdAt:string;
  favoriteTrails: Trail[];
  trailImages: TrailImage[];
  trailResouces: TrailResouce[];
  comments: Comment[];
  trails: Trail[];

  constructor(
    id:number = 0 ,
    username:string = "",
    role:string = "",
    password:string = "" ,
    enabled: boolean = false,
    favoriteTrailFood:string = "",
    firstName:string = "",
    lastName:string = "",
    imgUrl:string = "",
    createdAt:string = "",
    favoriteTrails: Trail[] = [],
    trailImages: TrailImage[] = [],
    trailResouces: TrailResouce[] = [],
    comments: Comment[] = [],
    trails: Trail[] = []
  ){
    this.id = id ,
    this.username = username,
    this.role = role,
    this.password = password,
    this.enabled = enabled,
    this.favoriteTrailFood = favoriteTrailFood,
    this.firstName = firstName,
    this.lastName = lastName,
    this.imgUrl = imgUrl,
    this.createdAt = createdAt,
    this.favoriteTrails = favoriteTrails,
    this.trailImages = trailImages,
    this.trailResouces = trailResouces,
    this.comments = comments,
    this.trails = trails
  }

}
