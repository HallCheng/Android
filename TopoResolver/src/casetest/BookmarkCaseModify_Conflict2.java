package casetest;

/**
 * �����޸�&�ƶ��޸�   �г�ͻ
 * 
 * last: ABC				1
 * cloud: �Ի�BCλ��: 		ACB	3
 * local: �޸� �Ի�ABλ�ã� 	BAC	2
 * expect: BAC or ACB
 * 
 * 
 * @author chedifier
 *
 */
public class BookmarkCaseModify_Conflict2 extends TestCase{

	@Override
	public void prepareCase() {
		caseData.add(new TestBookmarkBuilder("A")
				.dirty(0)
				.local_next("C")				
				.order_time(2L)
				.cloud_next("C")
				.cloud_order_time(3L)
				.build());
		
		caseData.add(new TestBookmarkBuilder("B")
				.dirty(0)
				.local_next("A")
				.order_time(2L)
				.cloud_next(null)
				.cloud_order_time(3L)
				.build());
		
		caseData.add(new TestBookmarkBuilder("C")
				.dirty(0)
				.local_next(null)
				.order_time(1L)
				.cloud_next("B")
				.cloud_order_time(3L)
				.build());
	}

}
