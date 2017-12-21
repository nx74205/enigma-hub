package de.couchkiwi.enigmahub.model;


import java.io.Serializable;

public class AmazonRequest implements Serializable {

    private Session session;
    private Request request;
    private Context context;
    private String version;

    // Getter and Setter start here

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class Session {
        private boolean lnew;
        private String sessionId;
        private Application application;
        private Attributes attributes;
        private User user;

        // Getter and Setter start here

        public boolean isLnew() {
            return lnew;
        }

        public void setLnew(boolean lnew) {
            this.lnew = lnew;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public Application getApplication() {
            return application;
        }

        public void setApplication(Application application) {
            this.application = application;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        public static class Application {
            private String applicationId;

            public String getApplicationId() {
                return applicationId;
            }

            public void setApplicationId(String applicationId) {
                this.applicationId = applicationId;
            }
        }
        public static class Attributes {
            private String dummy;

            public String getDummy() {
                return dummy;
            }

            public void setDummy(String dummy) {
                this.dummy = dummy;
            }
        }

        public static class User {
            private String userId;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
    public static class Request {
        private String type;
        private String requestId;
        private Intent intent;
        private String locale;
        private String timestamp;


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public Intent getIntent() {
            return intent;
        }

        public void setIntent(Intent intent) {
            this.intent = intent;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public static class Intent {
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

    }

    public static class Context {
        private AudioPlayer audioPlayer;
        private System system;

        public AudioPlayer getAudioPlayer() {
            return audioPlayer;
        }

        public void setAudioPlayer(AudioPlayer audioPlayer) {
            this.audioPlayer = audioPlayer;
        }

        public System getSystem() {
            return system;
        }

        public void setSystem(System system) {
            this.system = system;
        }

        public static class AudioPlayer {
            private String playeractivity;

            public String getPlayeractivity() {
                return playeractivity;
            }

            public void setPlayeractivity(String playeractivity) {
                this.playeractivity = playeractivity;
            }
        }

        public static class System {
            Session.Application application;
            Session.User user;
            Device device;

            public Session.Application getApplication() {
                return application;
            }

            public void setApplication(Session.Application application) {
                this.application = application;
            }

            public Session.User getUser() {
                return user;
            }

            public void setUser(Session.User user) {
                this.user = user;
            }

            public Device getDevice() {
                return device;
            }

            public void setDevice(Device device) {
                this.device = device;
            }

            public static class Device {
                private String supportedInterfaces;

                public String getSupportedInterfaces() {
                    return supportedInterfaces;
                }

                public void setSupportedInterfaces(String supportedInterfaces) {
                    this.supportedInterfaces = supportedInterfaces;
                }
            }
        }
    }

}
