GTEST_DIR = lib/googletest/googletest
OBJ_DIR	= obj
SRC_DIR = src/main
MAIN_SOURCES = $(wildcard $(SRC_DIR)/*.cpp)
MAIN_OBJECTS := $(MAIN_SOURCES:$(SRC_DIR)/%.cpp=$(OBJ_DIR)/%.o)  
TEST_DIR = src/test
TEST_SOURCES = $(wildcard $(TEST_DIR)/*.cpp) $(wildcard $(MOCKS_DIR)/*.h)
TEST_OBJECTS := $(TEST_SOURCES:$(TEST_DIR)/%.cpp=$(OBJ_DIR)/%.o)  
LIB_DIR = $(GTEST_DIR)/include
MOCKS_DIR = $(TEST_DIR)/mocks
CXXFLAGS += -g -std=c++17 -Wall -Wextra -pthread -isystem $(GTEST_DIR)/include -I $(MOCKS_DIR) -I $(LIB_DIR)
LFLAGS += -lpthread
DEP_DIR = dep
BIN_DIR = bin
TARGET = tests
GTEST_DIR = lib/googletest/googletest
GTEST_HEADERS = $(GTEST_DIR)/include/gtest/*.h \
                $(GTEST_DIR)/include/gtest/internal/*.h
GTEST_SRCS_ = $(GTEST_DIR)/src/*.cc $(GTEST_DIR)/src/*.h $(GTEST_HEADERS)

all : $(BIN_DIR)/$(TARGET)
	$(BIN_DIR)/$(TARGET)

$(OBJ_DIR)/gtest-all.o : $(GTEST_SRCS_)
	$(CXX) $(CPPFLAGS) -I$(GTEST_DIR) $(CXXFLAGS) -c \
            $(GTEST_DIR)/src/gtest-all.cc -o $@

$(BIN_DIR)/$(TARGET): $(TEST_OBJECTS) $(MAIN_OBJECTS) $(OBJ_DIR)/gtest-all.o
	mkdir -p $(BIN_DIR)
	$(CXX) $(LFLAGS) -I$(LIB_DIR) -o $@ $(TEST_OBJECTS) $(MAIN_OBJECTS) $(OBJ_DIR)/gtest-all.o
	@echo "Linking complete"

$(MAIN_OBJECTS) : $(OBJ_DIR)/%.o : $(SRC_DIR)/%.cpp
	$(CXX) -I$(LIB_DIR) -I$(MOCKS_DIR) -c $(CXXFLAGS) $< -o $@
	$(CXX) -I$(LIB_DIR) -I$(MOCKS_DIR) -MM -MT '$(OBJ_DIR)/$*.o' $(SRC_DIR)/$*.cpp > $(DEP_DIR)/$*.d

$(TEST_OBJECTS) : $(OBJ_DIR)/%.o : $(TEST_DIR)/%.cpp  
	mkdir -p $(OBJ_DIR)
	$(CXX) -I$(LIB_DIR) -c $(CXXFLAGS) $< -o $@
	$(CXX) -I$(LIB_DIR) -MM -MT '$(OBJ_DIR)/$*.o' $(TEST_DIR)/$*.cpp > $(DEP_DIR)/$*.d

clean :
	rm -f $(OBJ_DIR)/*.o $(DEP_DIR)/*.d $(BIN_DIR)/*
