import { Trail } from "./trail";
import { User } from "./user";

export class TrailImage {
  id: number;
  imageUrl: string;
  createAt: string;
  trail: Trail;
  user: User;


  constructor(
    id: number = 0,
    imageUrl: string = '',
    createAt: string = '',
    trail: Trail = new Trail(),
    user: User = new User()
  ) {
    this.id = id;
    this.imageUrl = imageUrl;
    this.createAt = createAt;
    this.trail = trail;
    this.user = user;
  }
}
