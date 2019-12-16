package com.lab.security;

import com.lab.enums.Role;

import java.util.*;

import static com.lab.constant.PageConstants.*;

public class SecurityConfig {
    private static Map<Role, List<String>> securityPages = new HashMap<>();

    static {
        securityPages.put(Role.LOADER, Arrays.asList("/" + LOADER_PAGE));
        securityPages.put(Role.CASHIER, Arrays.asList("/" + CASHIER_PAGE));
        securityPages.put(Role.CHIEF_CASHIER, Arrays.asList("/" + CHIEF_CASHIER_PAGE, "/" + REPORT_PAGE));
    }

    public static boolean isSecurePage(String page) {
        return securityPages.values().stream()
                .anyMatch(list -> list.stream()
                        .anyMatch(pageValue -> pageValue.equals(page)));
    }

    public static boolean hasPermission(String page, Role role) {
        return securityPages.getOrDefault(role, Collections.EMPTY_LIST)
                .stream()
                .anyMatch(securePage -> securePage.equals(page));
    }
}
