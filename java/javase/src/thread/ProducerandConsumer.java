package thread;

/**
 * �߳�ͨ�ŵ�Ӧ�ã��������⣺������/����������
 *
 * ������(Productor)����Ʒ������Ա(Clerk)����������(Customer)�ӵ�Ա��ȡ�߲�Ʒ��
 * ��Աһ��ֻ�ܳ��й̶������Ĳ�Ʒ(����:20���������������ͼ��������Ĳ�Ʒ����Ա
 * ���������ͣһ�£���������п�λ�Ų�Ʒ����֪ͨ�����߼����������������û�в�Ʒ �ˣ���Ա����������ߵ�һ�£���������в�Ʒ����֪ͨ��������ȡ�߲�Ʒ��
 *
 * ������ 1. �Ƿ��Ƕ��߳����⣿�ǣ��������̣߳��������߳� 2. �Ƿ��й������ݣ��ǣ���Ա�����Ʒ�� 3. ��ν���̵߳İ�ȫ���⣿ͬ������,�����ַ���
 * 4. �Ƿ��漰�̵߳�ͨ�ţ���
 * 
 * 
 * @Description: <Function>
 * @author karlieswift
 * @date 2020��4��11��
 * @version "13.0.1"
 */
class Clerk {

	private int productCount = 0;

	// ������Ʒ
	public synchronized void produceProduct() {

		if (productCount < 20) {
			productCount++;
			System.out.println(Thread.currentThread().getName() + ":��ʼ������" + productCount + "����Ʒ");

			notify();

		} else {
			// �ȴ�
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// ���Ѳ�Ʒ
	public synchronized void consumeProduct() {
		if (productCount > 0) {
			System.out.println(Thread.currentThread().getName() + ":��ʼ���ѵ�" + productCount + "����Ʒ");
			productCount--;

			notify();
		} else {
			// �ȴ�
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

class Producer extends Thread {// ������

	private Clerk clerk;

	public Producer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		System.out.println(getName() + ":��ʼ������Ʒ.....");

		while (true) {

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			clerk.produceProduct();
		}

	}
}

class Consumer extends Thread {// ������
	private Clerk clerk;

	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		System.out.println(getName() + ":��ʼ���Ѳ�Ʒ.....");

		while (true) {

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			clerk.consumeProduct();
		}
	}
}

public class ProducerandConsumer {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();

		Producer p1 = new Producer(clerk);
		p1.setName("������1");

		Consumer c1 = new Consumer(clerk);
		c1.setName("������1");
		Consumer c2 = new Consumer(clerk);
		c2.setName("������2");

		p1.start();
		c1.start();
		c2.start();

	}
}
