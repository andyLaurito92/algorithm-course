NUMBERS=${1:-10}
FILE_NAME=${2:-output.txt}

echo "Holaa"
echo "$NUMBERS and $FILE_NAME"

for i in `seq $NUMBERS`;
do
    echo $RANDOM >> $FILE_NAME
done 
