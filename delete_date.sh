#!/bin/bash

if [[ "$OSTYPE" == "darwin"* ]]; then
  # macOS
  find shared-parking-spot-app/src/main/java/com/parkingsharing/sql -type f -exec sed -i '' '2d' {} +
else
  # Linux
  find shared-parking-spot-app/src/main/java/com/parkingsharing/sql -type f -exec sed -i '2d' {} +
fi
