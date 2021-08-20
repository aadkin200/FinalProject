import { Trail } from "./trail";
import { User } from "./user";

export class TrailResource {

  id: number;
  user: User;
  resourceUrl: string;
  title: string;
  createdAt: string;
  enabled: boolean;
  trail: Trail;

constructor(
  id: number = 0,
  user: User = new User,
  createdAt: string = '',
  resourceUrl: string = '',
  title: string = '',
  enabled: boolean = true,
  trail: Trail = new Trail
){
  this.id = id;
  this.user = user;
  this.createdAt = createdAt;
  this.resourceUrl = resourceUrl;
  this.title = title;
  this.enabled = enabled;
  this.trail = trail;
}

}
