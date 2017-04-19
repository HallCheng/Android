package casetest;


/**
 * �����޸� && �ƶ��޸�&�ƶ�����
 * 
 * last: ABCD					1
 * cloud: �޸�AB ˳������E: EBACD	2
 * local: �޸�CD˳�� ABDC			3
 * 
 * expect: EBADC
 * 
 * @author chedifier
 *
 */
public class BookmarkCase5_cadd_lmodify extends TestCase{

	@Override
	public void prepareCase() {
		caseData.add(new TestBookmarkBuilder("A")
				.dirty(0)
				.local_next("B")
				.order_time(1L)
				.cloud_next("C")
				.cloud_order_time(2L)
				.build());
		
		caseData.add(new TestBookmarkBuilder("B")
				.dirty(0)
				.local_next("D")
				.order_time(3L)
				.cloud_next("A")
				.cloud_order_time(2L)
				.build());
		
		caseData.add(new TestBookmarkBuilder("C")
				.dirty(0)
				.local_next(null)
				.order_time(3L)
				.cloud_next("D")
				.cloud_order_time(1L)
				.build());
		caseData.add(new TestBookmarkBuilder("D")
				.dirty(0)
				.local_next("C")
				.order_time(3L)
				.cloud_next(null)
				.cloud_order_time(1L)
				.build());
		
		caseData.add(new TestBookmarkBuilder("E")
				.dirty(0)
				.local_next(null)
				.order_time(0L)
				.cloud_next("A")
				.cloud_order_time(2L)
				.build());
	}

}