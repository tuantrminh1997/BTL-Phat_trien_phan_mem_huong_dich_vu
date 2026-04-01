package org.ptit.soccerrest.util;

import org.ptit.soccerrest.domain.model.Coach;
import org.ptit.soccerrest.domain.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class XMLValidationHelper {

    private static final Logger log = LoggerFactory.getLogger(XMLValidationHelper.class);
    private static final String RULES_FILE = "validation-rules.xml";

    private static Document cachedDocument;

    public static List<String> validatePlayer(Player player) {
        return validateEntity("player", buildPlayerFieldMap(player));
    }

    public static List<String> validateCoach(Coach coach) {
        return validateEntity("coach", buildCoachFieldMap(coach));
    }

    private static synchronized Document getDocument() throws Exception {
        if (cachedDocument == null) {
            InputStream is = new ClassPathResource(RULES_FILE).getInputStream();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Tắt XXE để bảo mật
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            cachedDocument = builder.parse(is);
            cachedDocument.getDocumentElement().normalize();
            log.info("Đã tải validation-rules.xml thành công.");
        }
        return cachedDocument;
    }

    private static List<String> validateEntity(String entityName,
                                               Map<String, Object> fieldValues) {
        List<String> errors = new ArrayList<>();
        try {
            Document doc = getDocument();
            NodeList entities = doc.getElementsByTagName("entity");
            for (int i = 0; i < entities.getLength(); i++) {
                Element entity = (Element) entities.item(i);
                if (entityName.equals(entity.getAttribute("name"))) {
                    NodeList fields = entity.getElementsByTagName("field");
                    for (int j = 0; j < fields.getLength(); j++) {
                        Element field = (Element) fields.item(j);
                        String fieldName = field.getAttribute("name");
                        Object value = fieldValues.get(fieldName);
                        errors.addAll(applyRules(fieldName, field, value));
                    }
                    break;
                }
            }
        } catch (Exception e) {
            log.error("Lỗi khi đọc validation-rules.xml: {}", e.getMessage(), e);
        }
        return errors;
    }

    private static List<String> applyRules(String fieldName, Element field, Object value) {
        List<String> errors = new ArrayList<>();
        NodeList rules = field.getElementsByTagName("rule");

        for (int k = 0; k < rules.getLength(); k++) {
            Element rule = (Element) rules.item(k);
            String type = rule.getAttribute("type");
            String message = rule.getAttribute("message");
            String ruleValue = rule.getAttribute("value");
            String allowedValues = rule.getAttribute("values");

            switch (type) {
                case "required" -> {
                    if (value == null || value.toString().trim().isEmpty()) {
                        errors.add(message);
                    }
                }
                case "minLength" -> {
                    if (value != null && !value.toString().trim().isEmpty()
                            && value.toString().length() < Integer.parseInt(ruleValue)) {
                        errors.add(message);
                    }
                }
                case "maxLength" -> {
                    if (value != null && value.toString().length() > Integer.parseInt(ruleValue)) {
                        errors.add(message);
                    }
                }
                case "min" -> {
                    if (value != null) {
                        try {
                            BigDecimal bd = new BigDecimal(value.toString());
                            if (bd.compareTo(new BigDecimal(ruleValue)) < 0) {
                                errors.add(message);
                            }
                        } catch (NumberFormatException ignored) { /* skip non-numeric */ }
                    }
                }
                case "max" -> {
                    if (value != null) {
                        try {
                            BigDecimal bd = new BigDecimal(value.toString());
                            if (bd.compareTo(new BigDecimal(ruleValue)) > 0) {
                                errors.add(message);
                            }
                        } catch (NumberFormatException ignored) {
                        }
                    }
                }
                case "allowedValues" -> {
                    if (value != null && !value.toString().isBlank()) {
                        List<String> allowed = Arrays.asList(allowedValues.split(","));
                        if (!allowed.contains(value.toString())) {
                            errors.add(message);
                        }
                    }
                }
                default -> log.warn("Rule type '{}' trên field '{}' không được nhận dạng.",
                        type, fieldName);
            }
        }
        return errors;
    }

    private static Map<String, Object> buildPlayerFieldMap(Player player) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", player.getName());
        map.put("age", player.getAge());
        map.put("position", player.getPosition());
        map.put("jerseyNumber", player.getJerseyNumber());
        map.put("preferredFoot", player.getPreferredFoot());
        map.put("marketValue", player.getMarketValue());
        return map;
    }

    private static java.util.Map<String, Object> buildCoachFieldMap(Coach coach) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", coach.getName());
        map.put("age", coach.getAge());
        map.put("licenseLevel", coach.getLicenseLevel());
        map.put("experienceYears", coach.getExperienceYears());
        map.put("preferredFormation", coach.getPreferredFormation());
        return map;
    }
}

