
/**
 * @Title: RepairInformationDao.java
 * @Package com.sgai.modules.repair.dao
 * (用一句话描述该文件做什么)
 * @author XJ9001
 * @date 2018年1月20日
 * @Company 首自信--智慧城市创新中心
 * @version V1.0
 */

package com.sgai.property.wy.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wy.dto.MaintainInformationDto;
import com.sgai.property.wy.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @ClassName: RepairInformationDao
 * (这里用一句话描述这个类的作用)
 * @author XJ9001
 * @date 2018年1月20日
 * @Company 首自信--智慧城市创新中心  
 */
@Mapper
public interface RepairInformationDao extends CrudDao<RepairInformation> {


    /**
     * @Title: updateRepairStatus
     * (这里用一句话描述这个方法的作用)
     * @param @param repairInformation    参数
     * @return void    返回类型
     * @throws
     */

    void updateRepairStatus(RepairInformation repairInformation);


    /**
     * @Title: saveEquip
     * (这里用一句话描述这个方法的作用)
     * @param @param repairEquipment    参数
     * @return void    返回类型
     * @throws
     */

    void saveEquip(RepairEquipment repairEquipment);


    /**
     * @Title: deleteEquipment
     * (这里用一句话描述这个方法的作用)
     * @param @param id    参数
     * @return void    返回类型
     * @throws
     */

    void deleteEquipment(RepairInformation repairInformation);


    /**
     * @Title: getWorkloadList
     * (这里用一句话描述这个方法的作用)
     * @param @param maintainInformation
     * @param @return    参数
     * @return Page<MaintainInformationDto>    返回类型
     * @throws
     */

    List<MaintainInformationDto> findWorkList(
            MaintainInformationDto maintainInformation);


    /**
     * @Title: findName
     * (这里用一句话描述这个方法的作用)
     * @param @param repairPeopleName
     * @param @return    参数
     * @return List<Member>    返回类型
     * @throws
     */

    List<Member> findName(Member m);


    /**
     * @Title: manageChargeback
     * (退单修改状态 以及 去除 维修人)
     * @param @param repairInformation    参数
     * @return void    返回类型
     * @throws
     */

    void manageChargeback(
            RepairInformation repairInformation);


    /**
     * @Title: findPageByToday
     * (这里用一句话描述这个方法的作用)
     * @param @param repairInformation
     * @param @return    参数
     * @return List<RepairInformation>    返回类型
     * @throws
     */

    List<RepairInformation> findPageByToday(
            RepairInformation repairInformation);


    void updateComplainId(String id, String complainId);


	  
	    /**  
	    * @Title: getTypeByCode  
	    * (这里用一句话描述这个方法的作用)
	    * @param @param pCode
	    * @param @return    参数  
	    * @return List<RepairInformationType>    返回类型  
	    * @throws  
	    */  
	    
	List<RepairInformationType> getTypeByCode(String pCode);


		  
		    /**  
		    * @Title: findRepairList  
		    * (这里用一句话描述这个方法的作用)
		    * @param @param repair
		    * @param @return    参数  
		    * @return List<MaintainInformationDto>    返回类型  
		    * @throws  
		    */  
		    
		List<RepairInformation> findRepairList(RepairInformation repair);


				    /**  
				    * @Title: getTypeList  
				    * (这里用一句话描述这个方法的作用)
				    * @param @param repairInformationType
				    * @param @return    参数  
				    * @return List<RepairInformationType>    返回类型  
				    * @throws  
				    */  
				    
				List<RepairInformationType> getTypeList(RepairInformationType repairInformationType);


					  
					    /**  
					    * @Title: saveType  
					    * (这里用一句话描述这个方法的作用)
					    * @param @param repairInformationType    参数  
					    * @return void    返回类型  
					    * @throws  
					    */  
					    
					void saveType(RepairInformationType repairInformationType);


						  
						    /**  
						    * @Title: getTypeTotal  
						    * (这里用一句话描述这个方法的作用)
						    * @param @return    参数  
						    * @return int    返回类型  
						    * @throws  
						    */  
						    
						int getTypeTotal();


							  
							    /**  
							    * @Title: deleteType  
							    * (这里用一句话描述这个方法的作用)
							    * @param @param repairInformationType    参数  
							    * @return void    返回类型  
							    * @throws  
							    */  
							    
							void deleteType(RepairInformationType repairInformationType);


								  
								    /**  
								    * @Title: findById  
								    * (这里用一句话描述这个方法的作用)
								    * @param @param typeId
								    * @param @return    参数  
								    * @return RepairInformationType    返回类型  
								    * @throws  
								    */  
								    
								RepairInformationType findById(String typeId);

	RepairInformationType findByName(String typeName);
									  
									    /**  
									    * @Title: editType  
									    * (这里用一句话描述这个方法的作用)
									    * @param @param repairInformationType    参数  
									    * @return void    返回类型  
									    * @throws  
									    */  
									    
									void editType(RepairInformationType repairInformationType);


										  
										    /**  
										    * @Title: selectByGroup  
										    * (这里用一句话描述这个方法的作用)
										    * @param @param repairInformationDto
										    * @param @return    参数  
										    * @return List<RepairInformationDto>    返回类型  
										    * @throws  
										    */  
										    
										List<RepairInformationDto> selectByGroup(
                                                RepairInformationDto repairInformationDto);



											    /**
											    * @Title: selectComplainList
											    * (这里用一句话描述这个方法的作用)
											    * @param @param repairInformationDto
											    * @param @return    参数
											    * @return List<RepairInformationDto>    返回类型
											    * @throws
											    */

											List<RepairInformationDto> selectComplainList(
                                                    RepairInformationDto repairInformationDto);



												    /**
												    * @Title: selectAgainList
												    * (这里用一句话描述这个方法的作用)
												    * @param @param repairInformationDto
												    * @param @return    参数
												    * @return List<RepairInformationDto>    返回类型
												    * @throws
												    */

												List<RepairInformationDto> selectAgainList(
                                                        RepairInformationDto repairInformationDto);



													    /**
													    * @Title: selectOnlyList
													    * (这里用一句话描述这个方法的作用)
													    * @param @param repairInformationDto
													    * @param @return    参数
													    * @return List<RepairInformationDto>    返回类型
													    * @throws
													    */

													List<RepairInformationDto> selectOnlyList(
                                                            RepairInformationDto repairInformationDto);



														    /**
														    * @Title: getRepairTargetList
														    * (这里用一句话描述这个方法的作用)
														    * @param @param repairTargetDto
														    * @param @return    参数
														    * @return List<RepairTargetDto>    返回类型
														    * @throws
														    */

														List<RepairTargetDto> getRepairTargetList(
                                                                RepairTargetDto repairTargetDto);



															    /**
															    * @Title: getEveryBuildCount
															    * (这里用一句话描述这个方法的作用)
															    * @param @param r
															    * @param @return    参数
															    * @return int    返回类型
															    * @throws
															    */

															int getEveryBuildCount(RepairTargetDto r);



																    /**
																    * @Title: getEveryBuildName
																    * (这里用一句话描述这个方法的作用)
																    * @param @param r
																    * @param @return    参数
																    * @return RepairTargetDto    返回类型
																    * @throws
																    */

																RepairTargetDto getEveryBuildName(RepairTargetDto r);



																	    /**
																	    * @Title: getNum
																	    * (这里用一句话描述这个方法的作用)
																	    * @param @param rdto
																	    * @param @return    参数
																	    * @return int    返回类型
																	    * @throws
																	    */

																	int getNum(RepairTargetDto rdto);



																		    /**
																		    * @Title: getNumList
																		    * (这里用一句话描述这个方法的作用)
																		    * @param @param rdto
																		    * @param @return    参数
																		    * @return List<RepairTargetDto>    返回类型
																		    * @throws
																		    */

																		List<RepairTargetDto> getNumList(RepairTargetDto rdto);



																			    /**
																			    * @Title: getComplainNum
																			    * (这里用一句话描述这个方法的作用)
																			    * @param @param rdto
																			    * @param @return    参数
																			    * @return int    返回类型
																			    * @throws
																			    */

																			int getComplainNum(RepairTargetDto rdto);



																				    /**
																				    * @Title: getRepairPartList
																				    * (这里用一句话描述这个方法的作用)
																				    * @param @param repairCategoryDto
																				    * @param @return    参数
																				    * @return List<RepairCategoryDto>    返回类型
																				    * @throws
																				    */

																				List<RepairCategoryDto> getRepairPartList(
                                                                                        RepairCategoryDto repairCategoryDto);



																					    /**
																					    * @Title: getRepairClassList
																					    * (这里用一句话描述这个方法的作用)
																					    * @param @param repairCategoryDto
																					    * @param @return    参数
																					    * @return List<RepairCategoryDto>    返回类型
																					    * @throws
																					    */

																					List<RepairCategoryDto> getRepairClassList(
                                                                                            RepairCategoryDto repairCategoryDto);



																						    /**
																						    * @Title: getEveryCategoryCount
																						    * (这里用一句话描述这个方法的作用)
																						    * @param @param r1
																						    * @param @return    参数
																						    * @return int    返回类型
																						    * @throws
																						    */

																						int getEveryCategoryCount(
                                                                                                RepairTargetDto r1);

	List<RepairInformation>
			findCheckList(RepairInformation repairInformation);


										  
										   


		  
		
}
