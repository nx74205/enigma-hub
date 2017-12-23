package de.couchkiwi.enigmahub.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class AlexaCommonEndpoints implements Serializable {

    private List<Endpoints> endpoints;

    public List<Endpoints> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<Endpoints> endpoints) {
        this.endpoints = endpoints;
    }

    public static class Endpoints {

        private String endpointId;
        private String friendlyName;
        private String description;
        private String manufacturerName;
        private String[] displayCategories;

        private Cookie cookie;
        private List<Capabilities> capabilities;

        public String getManufacturerName() {
            return manufacturerName;
        }

        public void setManufacturerName(String manufacturerName) {
            this.manufacturerName = manufacturerName;
        }

        public String getEndpointId() {
            return endpointId;
        }

        public void setEndpointId(String endpointId) {
            this.endpointId = endpointId;
        }

        public String getFriendlyName() {
            return friendlyName;
        }

        public void setFriendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String[] getDisplayCategories() {
            return displayCategories;
        }

        public void setDisplayCategories(String[] displayCategories) {
            this.displayCategories = displayCategories;
        }

        public Cookie getCookie() {
            return cookie;
        }

        public void setCookie(Cookie cookie) {
            this.cookie = cookie;
        }

        public List<Capabilities> getCapabilities() {
            return capabilities;
        }

        public void setCapabilities(List<Capabilities> capabilities) {
            this.capabilities = capabilities;
        }

        public static class Cookie {

            private String extradetail1;
            private String extradetail2;
            private String extradetail3;
            private String extradetail4;

            public String getExtradetail1() {
                return extradetail1;
            }

            public void setExtradetail1(String extradetail1) {
                this.extradetail1 = extradetail1;
            }

            public String getExtradetail2() {
                return extradetail2;
            }

            public void setExtradetail2(String extradetail2) {
                this.extradetail2 = extradetail2;
            }

            public String getExtradetail3() {
                return extradetail3;
            }

            public void setExtradetail3(String extradetail3) {
                this.extradetail3 = extradetail3;
            }

            public String getExtradetail4() {
                return extradetail4;
            }

            public void setExtradetail4(String extradetail4) {
                this.extradetail4 = extradetail4;
            }
        }

        public static class Capabilities {

            private String type;

            @JsonProperty("interface")
            private String l_interface;
            private String version;
            private CapProperties properties;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getL_interface() {
                return l_interface;
            }

            public void setL_interface(String l_interface) {
                this.l_interface = l_interface;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public CapProperties getProperties() {
                return properties;
            }

            public void setProperties(CapProperties properties) {
                this.properties = properties;
            }

            public static class CapProperties {

                private List<Supported> supported;
                boolean proactivelyReported;
                boolean retrievable;

                public List<Supported> getSupported() {
                    return supported;
                }

                public void setSupported(List<Supported> supported) {
                    this.supported = supported;
                }

                public boolean isProactivelyReported() {
                    return proactivelyReported;
                }

                public void setProactivelyReported(boolean proactivelyReported) {
                    this.proactivelyReported = proactivelyReported;
                }

                public boolean isRetrievable() {
                    return retrievable;
                }

                public void setRetrievable(boolean retrievable) {
                    this.retrievable = retrievable;
                }

                public static class Supported {
                    private String name;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }

        }

    }
}
