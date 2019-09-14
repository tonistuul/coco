package ee.netgroup.coco.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
@Builder
public class Document {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String addedBy;
  private String modified;
}
