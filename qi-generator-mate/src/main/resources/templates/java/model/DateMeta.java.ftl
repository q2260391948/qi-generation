package ${basePackage}.model;

import lombok.Data;

/**
* @author:${author}
* @Date:2024/1/17 0:08
*  @Description:${description}
*/
@Data
public class DateMeta {
    <#list modelConfig.models as metaInfo>
    <#if metaInfo.description??>
    /**
    * ${metaInfo.description}
    */
    </#if>
        private ${metaInfo.type} ${metaInfo.fieldName}<#if metaInfo.defaultValue??></#if>;

    <#--        private ${metaInfo.type} ${metaInfo.fieldName}<#if metaInfo.defaultValue??> = ${metaInfo.defaultValue?c}</#if>;-->
<#--        private ${metaInfo.type} ${metaInfo.fieldName}<#if metaInfo.defaultValue??> = "${metaInfo.defaultValue?string}"</#if>;-->
    </#list>
}
