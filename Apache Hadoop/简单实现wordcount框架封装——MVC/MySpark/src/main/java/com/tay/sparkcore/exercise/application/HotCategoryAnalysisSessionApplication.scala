package com.tay.sparkcore.exercise.application

import com.tay.sparkFramwork.core.Tapplication
import com.tay.sparkcore.exercise.controller.{HotCategoryController, HotCategorySessionController}

/**
 * @author karlieswift 
 *         date: 2020/7/17 9:14 
 *         ClassName: HotCategoryAnalysisApplication
 * @version java "13.0.1"
 *
 *          在需求一的基础上，增加每个品类用户session的点击统计
 */
object HotCategoryAnalysisSessionApplication extends  App with Tapplication{

  /**
   * 热门品类排行top10
   */

  excute{
    val controller = new HotCategorySessionController
    controller.dispatcher()
  }
  /**
   * output:
   * (19,List((fde62452-7c09-4733-9655-5bd3fb705813,9), (85157915-aa25-4a8d-8ca0-9da1ee67fa70,9), (d4c2b45d-7fa1-4eff-8473-42cecdaffd62,9), (1b5e5ce7-cd04-4e78-9a6f-1c3dbb29ce39,8), (329b966c-d61b-46ad-949a-7e37142d384a,8), (5e3545a0-1521-4ad6-91fe-e792c20c46da,7), (46e6b58a-ad5b-4330-9e67-bc2651f9c5e2,7), (46d3e83c-11c1-4aa0-a2be-8999ead49879,7), (4d93913f-a892-490d-aa58-3a74b9099e29,7), (22e78a14-c5eb-45fe-a67d-2ce538814d98,7)))
   * (13,List((f736ee4a-cc14-4aa9-9a96-a98b0ad7cc3d,8), (329b966c-d61b-46ad-949a-7e37142d384a,8), (0f227059-7006-419c-87b0-b2057b94505b,7), (c0f70b31-fc3b-4908-af97-9b4936340367,7), (7eacce38-ffbc-4f9c-a3ee-b1711f8927b0,7), (1fb79ba2-4780-4652-9574-b1577c7112db,7), (632972a4-f811-4000-b920-dc12ea803a41,7), (1b5e5ce7-cd04-4e78-9a6f-1c3dbb29ce39,7), (eb7da133-b63a-45e6-a5d7-0cc0ab2ddb0f,6), (5e3545a0-1521-4ad6-91fe-e792c20c46da,6)))
   * (15,List((632972a4-f811-4000-b920-dc12ea803a41,10), (f34878b8-1784-4d81-a4d1-0c93ce53e942,8), (5e3545a0-1521-4ad6-91fe-e792c20c46da,8), (66a421b0-839d-49ae-a386-5fa3ed75226f,8), (9fa653ec-5a22-4938-83c5-21521d083cd0,8), (84d87899-dacf-48e4-8cc2-fab368b6984c,7), (524378b7-e676-43e6-a566-a5493a9058d4,7), (213bc2d5-be6b-49a3-9cb6-f9afc5b69b3d,7), (e306c00b-a6c5-44c2-9c77-15e919340324,7), (89278c1a-1e33-45aa-a4b7-33223e37e9df,7)))
   * (11,List((329b966c-d61b-46ad-949a-7e37142d384a,12), (2cd89b09-bae3-49b5-a422-9f9e0c12a040,7), (99f48b83-8f85-4bea-8506-c78cfe5a2136,7), (dc226249-ce13-442c-b6e4-bfc84649fff6,7), (4509c42c-3aa3-4d28-84c6-5ed27bbf2444,7), (c9f1b658-ac62-4263-b118-f95221c1189b,6), (5d2f3efb-be1c-4ee2-8fd5-545fd049e70c,6), (45e35ffa-f0e0-400e-a252-5605b4089625,6), (73203aee-de2e-443e-93cb-014e38c0d30c,6), (e7f9a91d-ff65-4b5f-9488-2a3195e1d0c6,6)))
   * (17,List((4509c42c-3aa3-4d28-84c6-5ed27bbf2444,12), (9bdc044f-8593-49fc-bbf0-14c28f901d42,8), (dd3704d5-a2f9-40c1-b491-87d24bbddbdb,8), (0416a1f7-350f-4ea9-9603-a05f8cfa0838,8), (bf390289-5c9d-4037-88b3-fdf386b3acd5,8), (1b5ac69b-5e00-4ff3-8a5c-6822e92ecc0c,8), (fde62452-7c09-4733-9655-5bd3fb705813,7), (08e65f4b-b2b0-4efd-8b66-92d2222465b9,7), (ab16e1e4-b3fc-4d43-9c95-3d49ec26d59c,7), (329b966c-d61b-46ad-949a-7e37142d384a,7)))
   * (20,List((199f8e1d-db1a-4174-b0c2-ef095aaef3ee,8), (7eacf77a-c019-4072-8e09-840e5cca6569,8), (d500c602-55db-4eb7-a343-3540c3ec7a36,7), (cde33446-095b-433c-927b-263ba7cd102a,7), (07b5fb82-da25-4968-9fd8-47485f4cf61e,7), (ab27e376-3405-46e2-82cb-e247bf2a16fb,7), (22e78a14-c5eb-45fe-a67d-2ce538814d98,7), (5e3545a0-1521-4ad6-91fe-e792c20c46da,7), (215bdee7-db27-458d-80f4-9088d2361a2e,7), (85157915-aa25-4a8d-8ca0-9da1ee67fa70,7)))
   * (12,List((73203aee-de2e-443e-93cb-014e38c0d30c,8), (22a687a0-07c9-4e84-adff-49dfc4fe96df,8), (a4b05ea2-2869-4f20-a82a-86352aa60e9f,8), (b4589b16-fb45-4241-a576-28f77c6e4b96,8), (a735881e-4c30-4ddc-a1d9-ef2069d5fb5b,7), (4c90a8a8-91d0-4888-908c-95dad1c5194e,7), (c32dc073-4454-4fcd-bf55-fbfcc8e650f3,7), (89278c1a-1e33-45aa-a4b7-33223e37e9df,7), (64285623-54ad-4a1f-ae84-d8f85ebf94c6,7), (ab16e1e4-b3fc-4d43-9c95-3d49ec26d59c,7)))
   * (9,List((199f8e1d-db1a-4174-b0c2-ef095aaef3ee,9), (5e3545a0-1521-4ad6-91fe-e792c20c46da,8), (329b966c-d61b-46ad-949a-7e37142d384a,8), (66c96daa-0525-4e1b-ba55-d38a4b462b97,7), (f205fd4f-f312-46d2-a850-26a16ac2734c,7), (8a0f8fe1-d0f4-4687-aff3-7ce37c52ab71,7), (cbdbd1a4-7760-4195-bfba-fa44492bf906,7), (4f0261cc-2cb1-40e0-9ffb-5587920c1084,7), (8f9723a3-833d-4103-a7ff-352cd17de067,7), (d56a53ff-a23e-404b-9919-5b7ef3df2664,7)))
   * (7,List((a41bc6ea-b3e3-47ce-98af-48169da7c91b,9), (f34878b8-1784-4d81-a4d1-0c93ce53e942,7), (9fa653ec-5a22-4938-83c5-21521d083cd0,7), (4dbd319c-3f44-48c9-9a71-a917f1d922c1,7), (95cb71b8-7033-448f-a4db-ae9861dd996b,7), (2d4b9c3e-2a9e-41b6-9573-9fde3533ed89,7), (aef6615d-4c71-4d39-8062-9d5d778e55f1,7), (4869238b-21b0-4bf5-b455-6ac3251381ac,6), (7d5363bc-3fe0-4953-a654-d7e89139820b,6), (fde62452-7c09-4733-9655-5bd3fb705813,6)))
   * (2,List((66c96daa-0525-4e1b-ba55-d38a4b462b97,11), (b4589b16-fb45-4241-a576-28f77c6e4b96,11), (f34878b8-1784-4d81-a4d1-0c93ce53e942,10), (25fdfa71-c85d-4c28-a889-6df726f08ffb,9), (0b17692b-d603-479e-a031-c5001ab9009e,8), (39cd210e-9d54-4315-80bf-bed004996861,8), (f666d6ba-b3e8-45b1-a269-c5d6c08413c3,8), (213bc2d5-be6b-49a3-9cb6-f9afc5b69b3d,8), (ab27e376-3405-46e2-82cb-e247bf2a16fb,8), (263af32c-a0f4-429d-9410-cc4cf15f16cf,7)))
   */
}
