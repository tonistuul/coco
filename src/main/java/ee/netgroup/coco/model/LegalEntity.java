package ee.netgroup.coco.model;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class LegalEntity {
  private String registryCode;
  private String name;
  private Collection<String> activities;
}
