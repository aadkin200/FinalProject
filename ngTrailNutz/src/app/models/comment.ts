import { Trail } from "./trail";
import { User } from "./user";

export class Comment {

  id: number;
  user?: User;
  message: string;
  createdAt?: number;
  updatedAt?: number;
  parentComment: Comment | any;
  replies: Comment[] | any;
  enabled: boolean;
  subject: string;
  trail?: Trail;

constructor(
  id: number = 0,
  user: User = new User,
  message: string = '',
  createdAt: number = 0,
  updatedAt: number = 0,
  parentComment = undefined,
  replies: Comment[] = [],
  enabled: boolean = true,
  subject: string = '',
  trail: Trail = new Trail
){
  this.id = id;
  this.user = user;
  this.message = message;
  this.createdAt = createdAt;
  this.updatedAt = updatedAt;
  this.parentComment = parentComment;
  this.replies = replies;
  this.enabled = enabled;
  this.subject = subject;
  this.trail = trail;
}
}
