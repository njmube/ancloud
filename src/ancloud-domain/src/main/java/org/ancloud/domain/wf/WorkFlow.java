package org.ancloud.domain.wf;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.ancloud.domain.BaseModel;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "workFlow")
@DynamicInsert
@DynamicUpdate
public class WorkFlow extends BaseModel{

  private static final long serialVersionUID = 2751845367690216253L;

}
