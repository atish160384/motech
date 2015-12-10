package org.motechproject.mds.helper;

import org.joda.time.DateTime;
import org.motechproject.mds.dto.FieldDto;
import org.motechproject.mds.dto.MetadataDto;
import org.motechproject.mds.dto.SchemaHolder;
import org.motechproject.mds.dto.TypeDto;
import org.motechproject.mds.util.Constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.motechproject.mds.util.Constants.Util.AUTO_GENERATED;
import static org.motechproject.mds.util.Constants.Util.AUTO_GENERATED_EDITABLE;
import static org.motechproject.mds.util.Constants.Util.TRUE;

/**
 * Helper class, responsible for generating default fields, that are a part of
 * each base entity.
 */
public final class EntityDefaultFieldsHelper {

    public static List<FieldDto> defaultFields(SchemaHolder schemaHolder) {
        TypeDto longType = schemaHolder.getType(Long.class);
        TypeDto stringType = schemaHolder.getType(String.class);
        TypeDto dateTimeType = schemaHolder.getType(DateTime.class);

        FieldDto idField = new FieldDto(Constants.Util.ID_FIELD_NAME, Constants.Util.ID_DISPLAY_FIELD_NAME, longType, true);
        idField.setReadOnly(true);
        idField.setMetadata(Collections.singletonList(new MetadataDto(AUTO_GENERATED, TRUE)));

        FieldDto creatorField = new FieldDto(Constants.Util.CREATOR_FIELD_NAME, Constants.Util.CREATOR_DISPLAY_FIELD_NAME, stringType, true);
        creatorField.setReadOnly(true);
        creatorField.addMetadata(new MetadataDto(AUTO_GENERATED, TRUE));

        FieldDto ownerField = new FieldDto(Constants.Util.OWNER_FIELD_NAME, Constants.Util.OWNER_DISPLAY_FIELD_NAME, stringType, false);
        ownerField.setReadOnly(true);
        ownerField.addMetadata(new MetadataDto(AUTO_GENERATED_EDITABLE, TRUE));

        FieldDto modifiedByField = new FieldDto(Constants.Util.MODIFIED_BY_FIELD_NAME, Constants.Util.MODIFIED_BY_DISPLAY_FIELD_NAME, stringType, true);
        modifiedByField.setReadOnly(true);
        modifiedByField.addMetadata(new MetadataDto(AUTO_GENERATED, TRUE));

        FieldDto modificationDateField = new FieldDto(Constants.Util.MODIFICATION_DATE_FIELD_NAME, Constants.Util.MODIFICATION_DATE_DISPLAY_FIELD_NAME, dateTimeType, true);
        modificationDateField.setReadOnly(true);
        modificationDateField.addMetadata(new MetadataDto(AUTO_GENERATED, TRUE));

        FieldDto creationDateField = new FieldDto(Constants.Util.CREATION_DATE_FIELD_NAME, Constants.Util.CREATION_DATE_DISPLAY_FIELD_NAME, dateTimeType, true);
        creationDateField.setReadOnly(true);
        creationDateField.addMetadata(new MetadataDto(AUTO_GENERATED, TRUE));

        return Arrays.asList(idField, creatorField, ownerField, modifiedByField, modificationDateField, creationDateField);
    }

    private EntityDefaultFieldsHelper() {

    }
}
