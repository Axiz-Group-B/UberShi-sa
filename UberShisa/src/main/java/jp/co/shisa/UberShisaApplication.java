package jp.co.shisa;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.service.RoomService;

@SpringBootApplication
public class UberShisaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = //これで、メインクラスでもアノテーション情報使える感じになる
		SpringApplication.run(UberShisaApplication.class, args);

		//サービスクラスを作ろう
		//RoomService roomS = context.getBean(RoomService.class);

		//30分配達員決まらなかったらそのorderのstatus更新
				//定期的に実行する処理
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						RoomService roomS = context.getBean(RoomService.class);
						//orderInfoから、statusが1のレコードをとる。orderDateTime取得して、現在時刻と比較。30分以上経ってたらstatusを3にする
						if(roomS.statusForHotel(1) != null) {//null回避。nullなら何も処理しない
							//現在時刻取得
							LocalDateTime nowTime = LocalDateTime.now();//計算にはこのクラスの方がよさそう
							List<OrderInfo>list = roomS.statusForHotel(1);
							for(OrderInfo o : list) {
								LocalDateTime orderTime = o.getDateTime().toLocalDateTime();
								if(orderTime.plusMinutes(1).isBefore(nowTime)) {//orderTime＋３０分して現在の時刻より前になったら、status３にする
									//DBアクセスして、このorderIdのstatusを3にする
									Timestamp dateTime = new Timestamp(System.currentTimeMillis());//引数のためにtimestamp型
									roomS.cansel(o.getOrderId(), 3, dateTime);
								}
							}
						}
					}
				};

				//実行する頻度とかの設定
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(task, 1000, 600000);
				//scheduleAtFixedRate(定期的に実行したいタスク,初回のタスク実行までの時間(ms),実行するタスクの間隔(ms))

	}

}
