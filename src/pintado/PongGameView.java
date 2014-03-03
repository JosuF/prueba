package pintado;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import juego.Bola;
import juego.Coordenada;
import juego.ElementoPong;
import juego.Raqueta;

public class PongGameView extends SurfaceView implements SurfaceHolder.Callback {

	private PongGameThread paintThread;
	private ElementoPong raquetaIzda;
	private ElementoPong raquetaDcha;
	private ElementoPong bola;

	public PongGameView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		raquetaIzda = new Raqueta(new Coordenada(50,getHeight()/2-50),20,100);
		raquetaDcha = new Raqueta(new Coordenada(getWidth()-70,getHeight()/2-50),20,100);
		bola = new Bola(new Coordenada(getWidth()/2-5,getHeight()/2-5),10,10);
		
		paintThread = new PongGameThread(getHolder(), this);
		paintThread.setRunning(true);
		paintThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		paintThread.setRunning(false);
		while (retry) {
			try {
				paintThread.join();
				retry = false;
			} catch (InterruptedException e) { }
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		
		canvas.drawColor(Color.BLACK);
		canvas.drawRect(raquetaIzda.getRectElemento(), paint);
		canvas.drawRect(raquetaDcha.getRectElemento(), paint);
		canvas.drawRect(bola.getRectElemento(), paint);
	}
}