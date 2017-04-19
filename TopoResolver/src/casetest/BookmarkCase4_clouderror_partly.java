package casetest;

/**
 * �ƶ����ݴ��ң��ƶ��޸ģ����ϴ�����δ
 * 
 * last: ABCD		1
 * cloud: �� D �ŵ�A��ǰ�棬 D->A, C->null �� �� C���������ϴ�ʧ���� : ABCD		2
 * 
 * local: δ�޸�ABCD	1
 * 
 * expect: 	ABCD
 * 
 * @author wxj_pc
 *
 */
public class BookmarkCase4_clouderror_partly extends TestCase{

	@Override
	public void prepareCase() {
		caseData.add(new TestBookmarkBuilder("A")
				.dirty(0)
				.local_next("B")
				.cloud_next("B")
				.order_time(1L)
				.build());
		
		caseData.add(new TestBookmarkBuilder("B")
				.dirty(0)
				.local_next("C")
				.cloud_next("C")
				.order_time(1L)
				.build());
		
		caseData.add(new TestBookmarkBuilder("C")
				.dirty(0)
				.local_next("D")
				.order_time(1L)
				.cloud_next("D")
				.cloud_order_time(1L)
				.build());
		caseData.add(new TestBookmarkBuilder("D")
				.dirty(0)
				.local_next(null)
				.order_time(1L)
				.cloud_next("A")
				.cloud_order_time(0L)
				.build());
	
	}

}