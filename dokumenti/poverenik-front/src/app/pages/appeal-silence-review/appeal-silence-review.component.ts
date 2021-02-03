import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppealSilenceService } from 'src/app/services/appeal-silence-service/appeal-silence.service';

@Component({
  selector: 'app-appeal-silence-review',
  templateUrl: './appeal-silence-review.component.html',
  styleUrls: ['./appeal-silence-review.component.css']
})
export class AppealSilenceReviewComponent implements OnInit {

  @Input() id: any;
  public html = '';
  constructor(private appealSilenceService: AppealSilenceService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getAppealSilence();
  }

  getAppealSilence(): void {
    this.appealSilenceService.toHtml(this.id).subscribe(
      (response => {
        if (!response) {
          alert('File not exist or is empty');
        }
        this.html = response;
      }),
      (error => {
        console.log(JSON.stringify(error));
        alert(error.error.message);
      })
    );
    }

}
