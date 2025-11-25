import { Pipe, PipeTransform } from '@angular/core';
import {DomSanitizer} from "@angular/platform-browser";
import { Observable } from 'rxjs';
import { QuestionarioDTO } from '../model';

/**
 * Generated class for the SafeHtmlPipe pipe.
 *
 * See https://angular.io/api/core/Pipe for more info on Angular Pipes.
 */
@Pipe({
  name: 'safeHtml',
})
export class SafeHtmlPipe implements PipeTransform {

  constructor(private sanitizer: DomSanitizer) {
  }

  transform(html: string | undefined) {
    if (!html) {
      return '';
    }
    console.log(html);
    return this.sanitizer.bypassSecurityTrustHtml(html);
  }

}
